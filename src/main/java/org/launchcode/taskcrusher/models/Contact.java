package org.launchcode.taskcrusher.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.launchcode.taskcrusher.enums.ContactStatus;

@Entity
@Table(name = "contact")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Contact {

    @Id
    @GeneratedValue
    private int contactId;

    private String name;

    @Enumerated(EnumType.STRING)
    private ContactStatus contactStatus;

    private String message;

}
