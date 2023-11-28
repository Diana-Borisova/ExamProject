package com.example.foodplanner.model.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UnitType {
	KILOGRAM("kg."),
	GRAM("g."),
	LITRE("l."),
	MILILITRE("ml."),
	TABLE_SPOON("tbl.s."),
	TEA_SPOON("tee s."),
//	WHOLE("vnt."),
	CUP("cup");
	
	public final String label;
	
	UnitType(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String getName() {
		return this.name();
	}
	
	@JsonCreator
    public static UnitType forValues(@JsonProperty("label") String label,
      @JsonProperty("name") String name) {
        for (UnitType unitType : UnitType.values()) {
            if (unitType.name().equals(name) && unitType.label.equals(label)) {
                return unitType;
            }
        }
        return null;
    }

}
