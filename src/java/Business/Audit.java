/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.Objects;


public class Audit {

    private int audit_id;
    private String description;

    /**
     *
     * @param audit_id new audit ID
     * @param description description text
     */
    public Audit(int audit_id, String description) {
        this.audit_id = audit_id;
        this.description = description;
    }

    /**
     *
     * @return audit ID
     */
    public int getAudit_id() {
        return audit_id;
    }

    /**
     *
     * @param audit_id set new audit ID
     */
    public void setAudit_id(int audit_id) {
        this.audit_id = audit_id;
    }

    /**
     *
     * @return description text
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description sets new description text
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Calculate the hashcode of this object, based only on its AuditID and
     * Description
     *
     * @return hash code generated from audit ID and description
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.audit_id;
        hash = 97 * hash + Objects.hashCode(this.description);
        return hash;
    }

    /**
     *
     * @param obj other Object, usually a Audit, to compare with.
     * @return true if the objects are of identical
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Audit other = (Audit) obj;
        if (this.audit_id != other.audit_id) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return a string representation of Audit object
     */
    @Override
    public String toString() {
        return "Audit{" + "audit_id=" + audit_id + ", description=" + description + '}';
    }

}
