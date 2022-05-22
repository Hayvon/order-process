package com.example.workflow.api.dto;

import com.google.gson.annotations.SerializedName;

public class StockEntry {
    @SerializedName("productid")
    public String ProductId;

    @SerializedName("name")
    public String Name;

    @SerializedName("amount")
    public Long Amount;
}
