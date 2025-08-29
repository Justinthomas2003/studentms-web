package com.example.studentms.ui;

import com.example.studentms.model.Student;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class StudentForm extends FormLayout {
    TextField name = new TextField("Name");
    TextField rollNumber = new TextField("Roll Number");
    TextField course = new TextField("Course");
    TextField email = new TextField("Email");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public StudentForm() {
        HorizontalLayout buttons = new HorizontalLayout(save, delete, close);
        add(name, rollNumber, course, email, buttons);
    }

    public void setStudent(Student student) {
        if (student != null) {
            name.setValue(student.getName() != null ? student.getName() : "");
            rollNumber.setValue(student.getRollNumber() != null ? student.getRollNumber() : "");
            course.setValue(student.getCourse() != null ? student.getCourse() : "");
            email.setValue(student.getEmail() != null ? student.getEmail() : "");
        }
    }

    public Student getStudent(Student existing) {
        existing.setName(name.getValue());
        existing.setRollNumber(rollNumber.getValue());
        existing.setCourse(course.getValue());
        existing.setEmail(email.getValue());
        return existing;
    }
}
