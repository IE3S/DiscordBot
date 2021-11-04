package club.ie3s.discord.utils;

@FunctionalInterface
public interface SingleValidation<K, T> {

    K validate(T argument);

}
