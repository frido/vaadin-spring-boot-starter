package com.example.application.views.about;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.function.ValueProvider;

public class EnumFieldFactory<E, T> extends FieldFactory<E>{

    private Function<E, T> getter;
    Class<T> type;

    protected EnumFieldFactory(String property, Class<T> type, Function<E, T> getter) {
        super(property);
        this.getter = getter;
        this.type = type;
    }

    @Override
    public Component apply(BeanValidationBinder<E> binder) {
        ComboBox<T> program = new ComboBox<>(getName());
        List<T> list = Arrays.stream(type.getEnumConstants()).map(x -> type.cast(x)).collect(Collectors.toList());
        program.setItems(list);
        binder.forField(program).bind(getName());
        return program;
    }

    @Override
    public ValueProvider<E, ?> getValueProvider() {
        return x -> getter.apply(x);
    }

}