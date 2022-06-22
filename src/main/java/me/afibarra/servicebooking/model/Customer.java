package me.afibarra.servicebooking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "customers")
public class Customer {

    public Customer() {
    }

    @Id
    private String customerId;
    private String firstName;
    private String lastName;
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
}
