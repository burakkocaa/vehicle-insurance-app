package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Motorpower")

public class Motorpower  {

private int motorPower;
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "MOTORPOWER_ID")
private int motorpowerId;

	public int getCarColor() {
        return motorPower;
    }
 
    public void setMotorPower(int motorPower) {
        this.motorPower = motorPower;
    }    
}
