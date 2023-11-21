package com.example.foodplanner.model.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UnitTypeEnum {
	KILOGRAM("kg."),
	GRAM("g."),
	LITRE("l."),
	MILILITRE("ml."),
	TABLE_SPOON("tbl.s."),
	TEA_SPOON("tee s."),
//	WHOLE("vnt."),
	CUP("cup");
	
	public final String label;
	
	private UnitTypeEnum(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getName() {
		return this.name();
	}
	
	@JsonCreator
    public static UnitTypeEnum forValues(@JsonProperty("label") String label,
										 @JsonProperty("name") String name) {
        for (UnitTypeEnum unitType : UnitTypeEnum.values()) {
            if (unitType.name().equals(name) && unitType.label.equals(label)) {
                return unitType;
            }
        }
        return null;
    }

}
