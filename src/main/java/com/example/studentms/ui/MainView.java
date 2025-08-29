package com.example.studentms.ui;

import com.example.studentms.model.Student;
import com.example.studentms.service.StudentService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {
    private final StudentService service;
    private final Grid<Student> grid = new Grid<>(Student.class);
    private final StudentForm form = new StudentForm();

    public MainView(StudentService service) {
        this.service = service;

        grid.setColumns("id", "name", "rollNumber", "course", "email");
        grid.asSingleSelect().addValueChangeListener(event ->
                editStudent(event.getValue()));

        Button addNewBtn = new Button("Add new Student");
        addNewBtn.addClickListener(e -> editStudent(new Student()));

        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(addNewBtn, mainContent);
        setSizeFull();

        updateList();

        // Form buttons
        form.save.addClickListener(e -> saveStudent());
        form.delete.addClickListener(e -> deleteStudent());
        form.close.addClickListener(e -> form.setVisible(false));

        form.setVisible(false);
    }

    private void updateList() {
        grid.setItems(service.findAll());
    }

    private void editStudent(Student student) {
        if (student == null) {
            form.setVisible(false);
        } else {
            form.setStudent(student);
            form.setVisible(true);
        }
    }

    private void saveStudent() {
        Student selected = grid.asSingleSelect().getValue();
        if (selected == null) selected = new Student();
        service.save(form.getStudent(selected));
        updateList();
        form.setVisible(false);
    }

    private void deleteStudent() {
        Student selected = grid.asSingleSelect().getValue();
        if (selected != null) {
            service.delete(selected);
            updateList();
            form.setVisible(false);
        }
    }
}
