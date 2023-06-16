package org.mawulidev;


import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Client implements Serializable {
    private String name ="Winfred mawuli Agbotey";
    private String phone ="0554704302";
    private double accountBalance = 5000000;
}
