package dev.sergevas.cg.gateway.registry.application.port.in;

import dev.sergevas.cg.gateway.shared.SelfValidating;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.StringJoiner;

public class DeleteRegisteredDeviceCommand extends SelfValidating<DeleteRegisteredDeviceCommand> {
    @NotNull
    private final String deviceId;

    public DeleteRegisteredDeviceCommand(String deviceId) {
        this.deviceId = deviceId;
        super.validateSelf();
    }

    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteRegisteredDeviceCommand that = (DeleteRegisteredDeviceCommand) o;
        return Objects.equals(deviceId, that.deviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DeleteRegisteredDeviceCommand.class.getSimpleName() + "[", "]")
                .add("deviceId='" + deviceId + "'")
                .toString();
    }
}