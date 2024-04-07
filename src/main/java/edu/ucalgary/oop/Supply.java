package edu.ucalgary.oop;

/*
## Attributes ##
- type: String
- quantity: int

## Methods ##
+ Supply(type: String, quantity: int)

+ getType(): String
+ getQuantity(): int

+ setType(type: string): void
+ setQuantity(quantity: int): void
 */

public class Supply {
    private String type;
    private int quantity;

    public Supply(String type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
