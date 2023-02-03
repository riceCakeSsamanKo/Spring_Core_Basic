package hello.core.beanoverlapping;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("carToy")
public class Car implements Toy{ }
