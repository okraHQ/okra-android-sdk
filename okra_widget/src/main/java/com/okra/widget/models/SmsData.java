package com.okra.widget.models;

public class SmsData {
    private String _id;
    private String thread_id;
    private String address;
    private int m_size;
    private String person;
    private String date;
    private String date_sent;
    private int protocol;
    private int read;
    private int status;
    private int type;
    private int reply_path_present;
    private String subject;
    private String body;
    private String service_center;
    private int locked;
    private int sub_id;
    private int error_Code;
    private String creator;
    private int seen;
    private int ipmsg_id;
    private String ref_id;
    private String total_len;
    private String rec_len;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getM_size() {
        return m_size;
    }

    public void setM_size(int m_size) {
        this.m_size = m_size;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(String date_sent) {
        this.date_sent = date_sent;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getReply_path_present() {
        return reply_path_present;
    }

    public void setReply_path_present(int reply_path_present) {
        this.reply_path_present = reply_path_present;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getService_center() {
        return service_center;
    }

    public void setService_center(String service_center) {
        this.service_center = service_center;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public int getError_Code() {
        return error_Code;
    }

    public void setError_Code(int error_Code) {
        this.error_Code = error_Code;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public int getIpmsg_id() {
        return ipmsg_id;
    }

    public void setIpmsg_id(int ipmsg_id) {
        this.ipmsg_id = ipmsg_id;
    }

    public String getRef_id() {
        return ref_id;
    }

    public void setRef_id(String ref_id) {
        this.ref_id = ref_id;
    }

    public String getTotal_len() {
        return total_len;
    }

    public void setTotal_len(String total_len) {
        this.total_len = total_len;
    }

    public String getRec_len() {
        return rec_len;
    }

    public void setRec_len(String rec_len) {
        this.rec_len = rec_len;
    }
}
