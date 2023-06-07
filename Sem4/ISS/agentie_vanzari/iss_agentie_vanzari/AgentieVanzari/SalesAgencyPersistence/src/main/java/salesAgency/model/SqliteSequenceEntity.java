package salesAgency.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "sqlite_sequence", schema = "main", catalog = "")
public class SqliteSequenceEntity {
    private Object name;
    private Object seq;

    @Basic
    @Column(name = "name")
    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    @Basic
    @Column(name = "seq")
    public Object getSeq() {
        return seq;
    }

    public void setSeq(Object seq) {
        this.seq = seq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SqliteSequenceEntity that = (SqliteSequenceEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(seq, that.seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, seq);
    }
}
