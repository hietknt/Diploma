import ru.diploma.algorithm.KohonenSOM;
import ru.diploma.algorithm.basic.*;
import ru.diploma.algorithm.initialization.items.type.ReadItemCreator;
import ru.diploma.algorithm.initialization.neurons.NeuronCreator;
import ru.diploma.algorithm.initialization.neurons.NeuronCreatorImpl;

import java.util.List;

public class Temp {

    private NeuronCreator neuronCreator = new NeuronCreatorImpl();
    private ReadItemCreator itemCreator = new ReadItemCreator();
    private KohonenSOM kohonenSOM;

    public void start(){
        List<Neuron> neurons = neuronCreator
                .createNeurons(
                        3,
                        3,
                        1,
                        RadiusForm.OCTAGON,
                        NeuronInitializeType.RANDOM
                );

        List<Item> items = itemCreator.create("data.txt");

        kohonenSOM = new KohonenSOM(
                items,
                neurons,
                0.5,
                MetricType.EUCLIDEAN
        );
        kohonenSOM.startLearning();

        System.out.println("\nNeurons: ");
        printNeurons(neurons);
        System.out.println("\nItems: ");
        printItems(items);
    }

    private void printNeurons(List<Neuron> neurons){
        for (int i = 0; i < neurons.size(); i++){
            System.out.println(neurons.get(i));
        }
    }

    private void printItems(List<Item> items){
        for (int i = 0; i < items.size(); i++){
            System.out.println(items.get(i));
        }
    }
}
