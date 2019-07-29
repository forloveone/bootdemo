package com.test.pojo.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Control {
    private String requesterName;
    private String requesterLanguage;
}
