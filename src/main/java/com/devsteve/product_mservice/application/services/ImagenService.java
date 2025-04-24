package com.devsteve.product_mservice.application.services;

import com.devsteve.product_mservice.application.ports.in.images.*;
import com.devsteve.product_mservice.application.ports.in.producto.crud.BuscarProductoPorIdUseCase;
import com.devsteve.product_mservice.domain.model.ImagenModel;
import com.devsteve.product_mservice.domain.ports.out.ImagenRepository;
import com.devsteve.product_mservice.domain.ports.out.ImagenStoragePort;
import com.devsteve.product_mservice.shared.exceptions.ImageUploadException;
import com.devsteve.product_mservice.shared.exceptions.ResourceNotFoundException;
import com.devsteve.product_mservice.shared.utils.ImagenUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public class ImagenService implements
        SubirImagenUseCase,
        EliminarImagenPorIdUseCase,
        EliminarImagenDeProductoUseCase,
        EliminarTodasImagenesDeProductoUseCase,
        ListarImagenesPorProductoUseCase,
        BuscarImagenPorIdUseCase {
    private final ImagenRepository imagenRepository;
    private final ImagenStoragePort imagenStoragePort;
    private final BuscarProductoPorIdUseCase buscarProductoPorIdUseCase;

    public ImagenService(ImagenRepository imagenRepository,
                         ImagenStoragePort imagenStoragePort,
                         BuscarProductoPorIdUseCase buscarProductoPorIdUseCase) {
        this.imagenRepository = imagenRepository;
        this.imagenStoragePort = imagenStoragePort;
        this.buscarProductoPorIdUseCase = buscarProductoPorIdUseCase;
    }

    @Override
    public ImagenModel subirImagen(Long productoId, MultipartFile archivo) {
        buscarProductoPorIdUseCase.buscarPorId(productoId);                 // valida producto existente
        ImagenUtils.validarArchivo(archivo);                     // valida formato y tamaño

        String key = UUID.randomUUID() + "-" + archivo.getOriginalFilename();
        String url;
        try {
            url = imagenStoragePort.subirArchivo(key, archivo);
        } catch (Exception e) {
            throw new ImageUploadException("Error al subir la imagen", e);
        }
        return imagenRepository.guardar(new ImagenModel(null, url, productoId));
    }

    @Override
    public ImagenModel buscarImagen(Long id) {
        return imagenRepository.buscarPorId(id);
    }

    @Override
    public List<ImagenModel> listarImagenes(Long productoId) {
        buscarProductoPorIdUseCase.buscarPorId(productoId); // valida que existe
        return imagenRepository.buscarPorProductoId(productoId);
    }

    // Eliminar
    @Override
    public void eliminarImagenPorId(Long imagenId) {
        ImagenModel img = buscarImagen(imagenId);
        eliminarFisicaYLogica(img);
    }

    @Override
    public void eliminarImagenDeProducto(Long productoId, Long imagenId) {
        ImagenModel img = buscarImagen(imagenId);
        if (!productoId.equals(img.getProductoId()))
            throw new ResourceNotFoundException("La imagen no pertenece al producto indicado");
        eliminarFisicaYLogica(img);
    }

    @Override
    public void eliminarTodasDeProducto(Long productoId) {
        buscarProductoPorIdUseCase.buscarPorId(productoId);
        imagenRepository.buscarPorProductoId(productoId).forEach(this::eliminarFisicaYLogica);
    }

    // Util interno
    private void eliminarFisicaYLogica(ImagenModel img) {
        imagenStoragePort.eliminarArchivo(ImagenUtils.extraerKey(img.getUrl()));
        imagenRepository.eliminar(img);
    }
}
