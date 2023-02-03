package hello.core.beanoverlapping;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Qualifier("robotToy")
public class Robot implements Toy{ }