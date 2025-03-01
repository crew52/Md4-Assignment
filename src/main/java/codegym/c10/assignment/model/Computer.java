package codegym.c10.assignment.model;

import javax.persistence.*;

@Entity
@Table(name = "computer")
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String producer;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    public Computer(Long id, String code, String name, String producer, Type type) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.producer = producer;
        this.type = type;
    }
    public Computer() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

