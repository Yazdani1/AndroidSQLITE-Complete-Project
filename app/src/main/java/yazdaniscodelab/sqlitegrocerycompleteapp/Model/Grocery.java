package yazdaniscodelab.sqlitegrocerycompleteapp.Model;

/**
 * Created by Yazdani on 12/14/2018.
 */

public class Grocery {

    private String name;
    private String quentity;
    private String dateItemAdded;
    private int id;

    public Grocery(){

    }

    public Grocery(String name, String quentity, String dateItemAdded, int id) {
        this.name = name;
        this.quentity = quentity;
        this.dateItemAdded = dateItemAdded;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuentity() {
        return quentity;
    }

    public void setQuentity(String quentity) {
        this.quentity = quentity;
    }

    public String getDateItemAdded() {
        return dateItemAdded;
    }

    public void setDateItemAdded(String dateItemAdded) {
        this.dateItemAdded = dateItemAdded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
