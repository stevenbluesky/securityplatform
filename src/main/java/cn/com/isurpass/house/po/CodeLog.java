package cn.com.isurpass.house.po;

import javax.persistence.*;

@Entity
@Table(name = "codelog")
public class CodeLog {
    @Id
    private String id;
    private Integer lastcode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLastcode() {
        return lastcode;
    }

    public void setLastcode(Integer lastcode) {
        this.lastcode = lastcode;
    }

    @Override
    public String toString() {
        return "CodeLog{" +
                "id='" + id + '\'' +
                ", lastcode=" + lastcode +
                '}';
    }
}
