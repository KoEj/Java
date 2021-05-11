package windowapp;

public class backpack
{

    int  elements_n;
    int backpack_size;
    int bp_value=0;
    java.util.ArrayList<Integer> values = new java.util.ArrayList<Integer>();
    java.util.ArrayList<Integer> size = new java.util.ArrayList<Integer>();
    java.util.ArrayList<Integer> ratio = new java.util.ArrayList<Integer>();
    java.util.ArrayList<Integer> queue = new java.util.ArrayList<Integer>();
    java.util.ArrayList<Integer> inout = new java.util.ArrayList<Integer>();

    public backpack (int t1, int t2)
    {
        elements_n = t1;
        backpack_size = t2;
    }
    public void init()
    {
    RandomNumberGenerator rng =new RandomNumberGenerator(1);

        for (int i = 0; i < elements_n; i++)
            values.add(rng.nextInt(1, backpack_size * 10));

        for (int i = 0; i < elements_n; i++)
            size.add(rng.nextInt(1, backpack_size / 2));

        for (int i = 0; i < elements_n; i++)
            ratio.add(values.get(i) / size.get(i));

        for (int i = 0; i < elements_n; i++)
            queue.add(i);

        for (int i = 0; i < elements_n; i++)
            inout.add(0);
    }

    public final void sort()
    {

        for (int i = 1; i < elements_n; ++i)
        {
            for (int j = elements_n - 1; j >= i; j--)
            {
                if (ratio.get(j - 1).compareTo(ratio.get(j)) > 0)
                {
                    int temp1 = ratio.get(j - 1);
                    int temp2 = queue.get(j - 1);
                    int temp3 = size.get(j - 1);
                    int temp4 = values.get(j - 1);
                    ratio.set(j - 1, ratio.get(j));
                    queue.set(j - 1, queue.get(j));
                    size.set(j - 1, size.get(j));
                    values.set(j - 1, values.get(j));

                    ratio.set(j, temp1);
                    queue.set(j, temp2);
                    size.set(j, temp3);
                    values.set(j, temp4);
                }
            }
        }
    }

    public final void put_in()
    {
        int loaded_weight = 0;

        for (int i = elements_n - 1; i >= 0; --i)
        {
            if ((loaded_weight + size.get(i)) <= backpack_size)
            {
                inout.set(i, 1);
                loaded_weight += size.get(i);
                bp_value += values.get(i);
            }
        }

    }

    public final void print_init()
    {
        System.out.printf("%1$s %2$s %3$s %4$s" + "\r\n", "lp", "value", "weight", "ratio");
        for (int i = 0; i < elements_n; i++)
        {
            System.out.printf("%1$2s %2$5s %3$4s %4$5s" + "\r\n", i + 1, values.get(i), size.get(i), ratio.get(i));
        }
    }

    public final void print_equipment()
    {
        System.out.println("Zapakowane przedmioty o łącznej wartosci " + bp_value + " to: ");

        System.out.printf("%1$s %2$s %3$s %4$s" + "\r\n", "lp", "value", "weight", "ratio");
        for (int i = 0; i < elements_n; i++)
        {
            if (inout.get(i).equals(1))
            {
                System.out.printf("%1$2s %2$5s %3$4s %4$5s" + "\r\n", queue.get(i), values.get(i), size.get(i), ratio.get(i));
            }
        }
    }

}
