package edu.unam.springsecurity.service.tarjeta;

import edu.unam.springsecurity.entities.Tarjeta;
import edu.unam.springsecurity.entities.Usuario;
import edu.unam.springsecurity.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Override
    public List<Tarjeta> obtenerTarjetasPorUsuario(Usuario usuario) {
        return tarjetaRepository.findByUsuario(usuario);
    }

    @Override
    public Tarjeta obtenerTarjetaPorIdYUsuario(Integer id, Usuario usuario) {
        return tarjetaRepository.findByIdAndUsuario(id, usuario);
    }

    @Override
    public Tarjeta guardarTarjeta(Tarjeta tarjeta) {
        return tarjetaRepository.save(tarjeta);
    }

    @Override
    public void actualizarSaldo(Tarjeta tarjeta, Float nuevoSaldo) {
        tarjeta.setSaldo(nuevoSaldo);
        tarjetaRepository.save(tarjeta);
    }
}
