package mod.model.entity_manager;

import java.sql.Connection;
import java.util.ArrayList;
import mod.model.Model;

public abstract class EntityManager {

    private int entityId;
    private final Connection con;

    public EntityManager(Connection con) {
        this.con = con;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public Connection getConnection() {
        return con;
    }

    public abstract int persist(Model model);

    public abstract int delete(Model model);

    public abstract int delete(int entityId);

    public abstract int update(Model model);

    public abstract ArrayList get();

    public abstract Model get(int entityId);

    public abstract Model get(String name);

    public abstract ArrayList get(Object filter);

    public abstract Model get(Model model);
}
