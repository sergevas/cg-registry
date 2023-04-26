package dev.sergevas.cg.gateway.registry.application.service;

import dev.sergevas.cg.gateway.registry.application.port.in.DeviceNotFoundException;
import dev.sergevas.cg.gateway.registry.application.port.in.UpdateDeviceRegistrationCommand;
import dev.sergevas.cg.gateway.registry.application.port.in.UpdateDeviceRegistrationUseCase;
import dev.sergevas.cg.gateway.registry.application.port.out.DeleteDeviceRegistration;
import dev.sergevas.cg.gateway.registry.application.port.out.LoadDeviceRegistration;
import dev.sergevas.cg.gateway.registry.application.port.out.SaveDeviceRegistration;
import dev.sergevas.cg.gateway.registry.domain.DeviceRegistration;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Component
public class UpdateDeviceRegistrationService implements UpdateDeviceRegistrationUseCase {

    private final LoadDeviceRegistration loadDeviceRegistration;
    private final SaveDeviceRegistration saveDeviceRegistration;
    private final DeleteDeviceRegistration deleteDeviceRegistration;

    public UpdateDeviceRegistrationService(LoadDeviceRegistration loadDeviceRegistration,
                                           SaveDeviceRegistration saveDeviceRegistration,
                                           DeleteDeviceRegistration deleteDeviceRegistration) {
        this.loadDeviceRegistration = loadDeviceRegistration;
        this.saveDeviceRegistration = saveDeviceRegistration;
        this.deleteDeviceRegistration = deleteDeviceRegistration;
    }

    @Override
    public DeviceRegistration updateDeviceRegistration(UpdateDeviceRegistrationCommand updateDeviceRegistrationCommand) {
        String deviceId = updateDeviceRegistrationCommand.getDeviceId();
        Optional.ofNullable(loadDeviceRegistration.load(deviceId))
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));
        deleteDeviceRegistration.delete(deviceId);
        DeviceRegistration deviceRegistration = updateDeviceRegistrationCommand.getDeviceRegistration();
        return this.saveDeviceRegistration.save(new DeviceRegistration(
                deviceRegistration.getDeviceId(),
                deviceRegistration.getDeviceType(),
                deviceRegistration.getDeviceUri(),
                deviceRegistration.getStatusUpdatePeriod(),
                deviceRegistration.getCreated(),
                OffsetDateTime.now(ZoneOffset.UTC),
                deviceRegistration.getDeviceTags()));
    }
}
