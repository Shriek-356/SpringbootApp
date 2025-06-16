package com.vdk.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "doctor_specialty", schema = "healthdb", indexes = {
        @Index(name = "specialty_id", columnList = "specialty_id")
})
public class DoctorSpecialty {
    @EmbeddedId
    private DoctorSpecialtyId id;

    @MapsId("doctorId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @MapsId("specialtyId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;

    public DoctorSpecialtyId getId() {
        return id;
    }

    public void setId(DoctorSpecialtyId id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

}