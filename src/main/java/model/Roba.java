package model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by android on 1.10.16..
 */

@DatabaseTable(tableName = "roba")
public class Roba {

    @DatabaseField(foreign = true,foreignAutoRefresh = true,canBeNull = false)
    private Avion avion;
}
