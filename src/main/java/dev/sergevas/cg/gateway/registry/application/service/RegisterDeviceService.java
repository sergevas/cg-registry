package dev.sergevas.cg.gateway.registry.application.service;

import dev.sergevas.cg.gateway.registry.application.port.in.RegisterDeviceCommand;
import dev.sergevas.cg.gateway.registry.application.port.in.RegisterDeviceUseCase;
import dev.sergevas.cg.gateway.registry.application.port.out.LoadDeviceRegistration;
import dev.sergevas.cg.gateway.registry.application.port.out.SaveDeviceRegistration;
import dev.sergevas.cg.gateway.registry.domain.DeviceRegistration;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RegisterDeviceService implements RegisterDeviceUseCase {

    @Inject
    private LoadDeviceRegistration loadDeviceRegistration;
    @Inject
    private SaveDeviceRegistration saveDeviceRegistration;

    @Override
    public DeviceRegistration register(RegisterDeviceCommand registerDeviceCommand) {
        String deviceId = registerDeviceCommand.getDeviceRegistration().getDeviceId();
//        if (loadDeviceRegistration.load(registerDeviceCommand.getDeviceRegistration().getDeviceId()) != null) {
//            throw new DeviceAlreadyRegisteredException(deviceId);
//        }
        return saveDeviceRegistration.save(registerDeviceCommand.getDeviceRegistration());
    }
}