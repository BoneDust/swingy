package com;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Setter
@Getter

public class test
{
    @Min(value = 10, message = "minimum value is 10")
    @Max(value = 100, message = "the maximum is 100")
    private int value;

    public test(int value)
    {
        this.value = value;
    }
}
