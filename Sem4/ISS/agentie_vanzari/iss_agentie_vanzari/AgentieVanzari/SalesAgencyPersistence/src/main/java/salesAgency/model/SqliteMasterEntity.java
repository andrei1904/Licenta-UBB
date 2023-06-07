package salesAgency.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "sqlite_master", schema = "main", catalog = "")
public class SqliteMasterEntity {
    private String type;
    private String name;
    private String tblName;
    private Object rootpage;
    private String sql;

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "tbl_name")
    public String getTblName() {
        return tblName;
    }

    public void setTblName(String tblName) {
        this.tblName = tblName;
    }

    @Basic
    @Column(name = "rootpage")
    public Object getRootpage() {
        return rootpage;
    }

    public void setRootpage(Object rootpage) {
        this.rootpage = rootpage;
    }

    @Basic
    @Column(name = "sql")
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SqliteMasterEntity that = (SqliteMasterEntity) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(tblName, that.tblName) &&
                Objects.equals(rootpage, that.rootpage) &&
                Objects.equals(sql, that.sql);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, tblName, rootpage, sql);
    }
}
