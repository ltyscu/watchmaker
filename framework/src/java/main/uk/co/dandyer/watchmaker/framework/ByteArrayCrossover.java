package uk.co.dandyer.watchmaker.framework;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import uk.co.dandyer.maths.random.RandomSequence;

/**
 * Cross-over with a configurable number of points (fixed or random) for
 * arrays of primitive bytes.
 * @author Daniel Dyer
 */
public class ByteArrayCrossover extends AbstractCrossover<byte[]>
{
    /**
     * Default is single-point cross-over.
     */
    public ByteArrayCrossover()
    {
        this(1);
    }


    /**
     * Cross-over with a fixed number of cross-over points.
     */
    public ByteArrayCrossover(int crossoverPoints)
    {
        super(crossoverPoints);
    }


    /**
     * Cross-over with a variable number of cross-over points.
     */
    public ByteArrayCrossover(RandomSequence<Integer> crossoverPointsVariable)
    {
        super(crossoverPointsVariable);
    }


    protected List<byte[]> reproduce(byte[] parent1,
                                     byte[] parent2,
                                     int numberOfCrossoverPoints,
                                     Random rng)
    {
        if (parent1.length != parent2.length)
        {
            throw new IllegalArgumentException("Cannot perform cross-over with different length parents.");
        }
        byte[] offspring1 = new byte[parent1.length];
        System.arraycopy(parent1, 0, offspring1, 0, parent1.length);
        byte[] offspring2 = new byte[parent2.length];
        System.arraycopy(parent2, 0, offspring2, 0, parent2.length);
        // Apply as many cross-overs as required.
        byte[] temp = new byte[parent1.length];
        for (int i = 0; i < numberOfCrossoverPoints; i++)
        {
            int crossoverIndex = rng.nextInt(parent1.length);
            System.arraycopy(offspring1, 0, temp, 0, crossoverIndex);
            System.arraycopy(offspring2, 0, offspring1, 0, crossoverIndex);
            System.arraycopy(temp, 0, offspring2, 0, crossoverIndex);
        }
        List<byte[]> result = new ArrayList<byte[]>(2);
        result.add(offspring1);
        result.add(offspring2);
        return result;
    }
}
