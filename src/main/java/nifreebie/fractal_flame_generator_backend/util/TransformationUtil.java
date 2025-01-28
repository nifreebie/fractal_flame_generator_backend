package nifreebie.fractal_flame_generator_backend.util;

import nifreebie.fractal_flame_generator_backend.model.transformations.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class TransformationUtil {
    private static final Map<TransformationType, Transformation> transformations = new EnumMap<>(TransformationType.class);

    static {
        transformations.put(TransformationType.BUBBLE, new BubbleTransformation());
        transformations.put(TransformationType.EXPONENTIAL, new ExponentionalTransformation());
        transformations.put(TransformationType.HEART, new HeartTransformation());
        transformations.put(TransformationType.HORSESHOE, new HorsehoeTransformation());
        transformations.put(TransformationType.HYPERBOLIC, new HyperbolicTransformation());
        transformations.put(TransformationType.LINEAR, new LinearTransformation());
        transformations.put(TransformationType.POLAR, new PolarTransformation());
        transformations.put(TransformationType.SINUSOIDAL, new SinusoidalTransformation());
        transformations.put(TransformationType.SPHERICAL, new SphericalTransformation());
        transformations.put(TransformationType.SWIRL, new SwirlTransformation());
    }

    static public List<Transformation> getTransformations(List<TransformationType> transformationTypes) {
        List<Transformation> newTransformations = new ArrayList<>();
        for(TransformationType type: transformationTypes) {
            newTransformations.add(transformations.get(type));
        }
        return newTransformations;
    }
}
