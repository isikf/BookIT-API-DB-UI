package api_TestNg.tests.end_points.campus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Rooms {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("withTV")
    @Expose
    private Boolean withTV;
    @SerializedName("withWhiteBoard")
    @Expose
    private Boolean withWhiteBoard;

    /**
     * No args constructor for use in serialization
     *
     */
    public Rooms() {
    }

    /**
     *
     * @param withTV
     * @param name
     * @param description
     * @param id
     * @param withWhiteBoard
     * @param capacity
     */
    public Rooms(Integer id, String name, String description, Integer capacity, Boolean withTV, Boolean withWhiteBoard) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.withTV = withTV;
        this.withWhiteBoard = withWhiteBoard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getWithTV() {
        return withTV;
    }

    public void setWithTV(Boolean withTV) {
        this.withTV = withTV;
    }

    public Boolean getWithWhiteBoard() {
        return withWhiteBoard;
    }

    public void setWithWhiteBoard(Boolean withWhiteBoard) {
        this.withWhiteBoard = withWhiteBoard;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("description", description).append("capacity", capacity).append("withTV", withTV).append("withWhiteBoard", withWhiteBoard).toString();
    }

}