package br.com.motix.models;

import br.com.motix.models.enums.BikeType;
import br.com.motix.models.enums.Sectors;
import jakarta.persistence.*;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "MOTORCYCLE")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Motorcycle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter @Setter
    private UUID id;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(length = 15) @NotNull
    private Sectors sector;

    @Getter @Setter
    @Column(length = 10) @NotNull
    private String position;

    @Getter @Setter//<---------------------------------------------------
    @Column(length = 10, unique = true) @NotNull
    private String plate;

    @Getter @Setter
    private boolean isPlateReadable;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(length = 15) @NotNull
    private BikeType type;

    public Motorcycle(UUID id, Sectors sector, String position, String plate, boolean isPlateReadable, BikeType type) {
        this.id = id;
        this.sector = sector;
        this.position = position;
        readPlate(plate);
        this.isPlateReadable = isPlateReadable;
        this.type = type;
    }

    public String readPlate(String plate) {   //<------------------------------------------------
        if (plate != null) {
            setPlateReadable(true);
            return this.plate = plate;
        }else{
            setPlateReadable(false);
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Motorcycle that = (Motorcycle) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
