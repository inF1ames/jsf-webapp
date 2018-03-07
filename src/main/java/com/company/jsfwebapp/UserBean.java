package com.company.jsfwebapp;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.*;

@ManagedBean
@ApplicationScoped
public class UserBean implements Serializable {

    private String email;
    private String password;
    private List<Event> events = new ArrayList<>();
    private List<Event> currentEvents;
    @ManagedProperty("#{dateRange}")
    private DateRange dateRange;

    private Map<String, String> users = new HashMap<String, String>() {{
        put("admin@gmail.com", "admin");
    }};

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (users.containsKey(this.email) &&
                this.password.equals(users.get(this.email))) {
            context.getExternalContext().getSessionMap().put("user", email);
            try {
                context.getExternalContext().redirect("main.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            context.addMessage(null, new FacesMessage("Неправильный логин или пароль"));
        }
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void registration() throws IOException {
        users.put(this.email, this.password);
        login();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEventsByRange(DateRange dateRange) throws ParseException {
        Set<Event> set = new TreeSet<>();
        for (Event event : events) {
            if (event.getDate() >= dateRange.getStartDate() && event.getDate() <= dateRange.getEndDate()) {
                set.add(event);
            }
        }
        return currentEvents = new ArrayList<>(set);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }

    public List<Event> getCurrentEvents() {
        return currentEvents;
    }

    public void setCurrentEvents(List<Event> currentEvents) {
        this.currentEvents = currentEvents;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", events=" + events +
                ", dateRange=" + dateRange +
                ", users=" + users +
                '}';
    }
}