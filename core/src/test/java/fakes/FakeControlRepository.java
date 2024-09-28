package fakes;

import hiof.gruppe25.core.models.control.Control;
import hiof.gruppe25.core.persistenceinterfaces.iRepository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FakeControlRepository implements iRepository<Control> {
    HashMap<Integer, Control> controls = new HashMap<>();

    @Override
    public Control getById(int id) {
        return controls.get(id);
    }

    @Override
    public Control getById(UUID id) {
        return null;
    }

    @Override
    public void add(Control element) {
        controls.put(element.getControlNumber(), element);
    }

    @Override
    public void delete(int id) {
        controls.remove(id);
    }

    @Override
    public void delete(UUID UUID) {
    }

    @Override
    public void merge(Control element) {

    }

    @Override
    public void mergeAll(List<Control> typeList) {

    }

    @Override
    public List<Control> getAll() {
        return controls.values().stream().toList();
    }
}
