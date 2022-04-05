package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRolenameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Administrator"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.add(new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Administrator"));
    }

    @Test
    public void whenReplaceThenRolenameIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.replace("1", new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("User"));
    }

    @Test
    public void whenNoReplaceUserThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.replace("10", new Role("10", "User"));
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Administrator"));
    }

    @Test
    public void whenDeleteRoleThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRolenameIsAdministrator() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Administrator"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRolename(), is("Administrator"));
    }

}