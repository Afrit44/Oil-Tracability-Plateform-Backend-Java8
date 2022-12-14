package com.Oil4Med.Oil4Med.Model.Types;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Temperature {

    private Date dateTemperature;

    private float valueTemperature;

}
