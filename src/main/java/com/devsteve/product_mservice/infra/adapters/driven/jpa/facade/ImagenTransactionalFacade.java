package com.devsteve.product_mservice.infra.adapters.driven.jpa.facade;

import com.devsteve.product_mservice.application.ports.in.images.*;
import com.devsteve.product_mservice.application.ports.in.producto.delegate.EliminarImagenesProductoDelegate;
import com.devsteve.product_mservice.domain.model.ImagenModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImagenTransactionalFacade implements
        SubirImagenUseCase,
        EliminarImagenPorIdUseCase,
        EliminarImagenDeProductoUseCase,
        EliminarTodasImagenesDeProductoUseCase,
        ListarImagenesPorProductoUseCase,
        BuscarImagenPorIdUseCase,
        EliminarImagenesProductoDelegate {
    private final SubirImagenUseCase subirImagenUseCase;
    private final EliminarImagenPorIdUseCase eliminarImagenPorIdUseCase;
    private final EliminarImagenDeProductoUseCase eliminarImagenDeProductoUseCase;
    private final EliminarTodasImagenesDeProductoUseCase eliminarTodasImagenesDeProductoUseCase;
    private final ListarImagenesPorProductoUseCase listarImagenesPorProductoUseCase;
    private final BuscarImagenPorIdUseCase buscarImagenPorIdUseCase;

    @Override
    @Transactional(readOnly = true)
    public ImagenModel buscarImagen(Long imagenId) {
        return buscarImagenPorIdUseCase.buscarImagen(imagenId);
    }

    @Override
    @Transactional
    public void eliminarImagenDeProducto(Long productoId, Long imagenId) {
        eliminarImagenDeProductoUseCase.eliminarImagenDeProducto(productoId, imagenId);
    }

    @Override
    @Transactional
    public void eliminarImagenPorId(Long imagenId) {
        eliminarImagenPorIdUseCase.eliminarImagenPorId(imagenId);
    }

    @Override
    @Transactional
    public void eliminarTodasDeProducto(Long productoId) {
        eliminarTodasImagenesDeProductoUseCase.eliminarTodasDeProducto(productoId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImagenModel> listarImagenes(Long productoId) {
        return listarImagenesPorProductoUseCase.listarImagenes(productoId);
    }

    @Override
    @Transactional
    public ImagenModel subirImagen(Long productoId, MultipartFile file) {
        return subirImagenUseCase.subirImagen(productoId, file);
    }

    @Override
    @Transactional
    public void eliminarImagenesDeProducto(Long productoId) {
        eliminarTodasImagenesDeProductoUseCase.eliminarTodasDeProducto(productoId);
    }
}
