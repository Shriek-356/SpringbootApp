package com.vdk.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DoctorClinicId implements Serializable {
    private static final long serialVersionUID = -3151442843503375164L;
    @Column(name = "doctor_id", nullable = false)
    private Integer doctorId;

    @Column(name = "clinic_id", nullable = false)
    private Integer clinicId;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getClinicId() {
        return clinicId;
    }

    public void setClinicId(Integer clinicId) {
        this.clinicId = clinicId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DoctorClinicId entity = (DoctorClinicId) o;
        return Objects.equals(this.clinicId, entity.clinicId) &&
                Objects.equals(this.doctorId, entity.doctorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clinicId, doctorId);
    }

}