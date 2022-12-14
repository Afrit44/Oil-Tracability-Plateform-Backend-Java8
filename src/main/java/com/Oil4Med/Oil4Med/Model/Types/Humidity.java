package com.Oil4Med.Oil4Med.Model.Types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Date;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Humidity {

    private Date dateHumidity;

    private float valueHumidity;

}
