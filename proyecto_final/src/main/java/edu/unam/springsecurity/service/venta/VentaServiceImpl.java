package edu.unam.springsecurity.service.venta;

import edu.unam.springsecurity.entities.Usuario;
import edu.unam.springsecurity.entities.Venta;
import edu.unam.springsecurity.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public Venta guardarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public List<Venta> obtenerVentasFinalizadasPorUsuario(Usuario usuario) {
        return ventaRepository.findByUsuarioAndFinalizadaTrue(usuario);
    }

    @Override
    public Venta obtenerVentaPorIdYUsuario(Integer id, Usuario usuario) {
        return ventaRepository.findByIdAndUsuario(id, usuario);
    }

}
