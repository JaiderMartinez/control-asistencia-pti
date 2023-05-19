package com.colegio.asistencia.domain.model;

import java.time.LocalTime;

public record EnvironmentModel(Long codePti, String description, String classroom, LocalTime startTime, LocalTime endTime, UserModel userModel) {
}
