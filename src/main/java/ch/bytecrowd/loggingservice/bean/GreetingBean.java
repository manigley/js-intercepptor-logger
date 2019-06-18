package ch.bytecrowd.loggingservice.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named("greetingBean")
public class GreetingBean implements Serializable {

	private static final long serialVersionUID = 6278230516542518427L;

	private String name;

	public void greet() {
		FacesMessage message = new FacesMessage(String.format("Hello %s", name));
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
