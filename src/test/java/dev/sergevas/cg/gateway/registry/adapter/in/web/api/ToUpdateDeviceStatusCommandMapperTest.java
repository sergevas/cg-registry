package dev.sergevas.cg.gateway.registry.adapter.in.web.api;

import dev.sergevas.cg.gateway.registry.application.port.in.UpdateDeviceStatusCommand;
import dev.sergevas.cg.gateway.registry.domain.StateType;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ToUpdateDeviceStatusCommandMapperTest {

    @Test
    void should_ReturnUpdateDeviceStatusCommand_IfInputOk() {
        UpdateDeviceStatusCommand expected = new UpdateDeviceStatusCommand("0001", StateType.ACTIVE);
        ToUpdateDeviceStatusCommandMapper mapper = new ToUpdateDeviceStatusCommandMapper();
        assertEquals(expected, mapper.map("0001", DeviceStateType.ACTIVE));
    }

    @Test
    void should_ThrowException_IfInputInvalid() {
        ToUpdateDeviceStatusCommandMapper mapper = new ToUpdateDeviceStatusCommandMapper();
        ConstraintViolationException cve = assertThrows(ConstraintViolationException.class,
                () -> mapper.map("", null));
        assertEquals("deviceId: must not be blank, stateType: must not be null", cve.getMessage());
    }
}
