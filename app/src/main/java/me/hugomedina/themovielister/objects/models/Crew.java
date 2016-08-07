
package me.hugomedina.themovielister.objects.models;

public class Crew {

    private String credit_id;
    private String department;
    private Integer id;
    private String job;
    private String name;
    private Object profile_path;

    /**
     * 
     * @return
     *     The credit_id
     */
    public String getCredit_id() {
        return credit_id;
    }

    /**
     * 
     * @param credit_id
     *     The credit_id
     */
    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    /**
     * 
     * @return
     *     The department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * 
     * @param department
     *     The department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The job
     */
    public String getJob() {
        return job;
    }

    /**
     * 
     * @param job
     *     The job
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The profile_path
     */
    public Object getProfile_path() {
        return profile_path;
    }

    /**
     * 
     * @param profile_path
     *     The profile_path
     */
    public void setProfile_path(Object profile_path) {
        this.profile_path = profile_path;
    }

}
