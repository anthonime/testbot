package com.github.anthonime.testbot.definitions.data;

/**
 * Created by schio on 9/13/2017.
 */
public class ObjectInstanceIdentifier {

    private ObjectDefinitionIdentifier identifier;
    private int num;

    public ObjectInstanceIdentifier(ObjectDefinitionIdentifier identifier, int num) {
        this.identifier = identifier;
        this.num = num;
    }

    public String getIdentifier() {
        return identifier.getIdentifier() + "#" + num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectInstanceIdentifier that = (ObjectInstanceIdentifier) o;

        if (num != that.num) return false;
        return identifier.equals(that.identifier);
    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + num;
        return result;
    }

    @Override
    public String toString() {
        return getIdentifier();
    }
}
