public class Main {
    public static void merge(FilaCircular a, FilaCircular b, FilaCircular r) {
        if (a == b || r == a || r == b) {
            throw new IllegalArgumentException("As filas A, B e R devem ser diferentes.");
        }
        if (!r.vazia()) {
            throw new IllegalArgumentException("A fila R deve estar vazia antes do merge.");
        }
        if (r.capacidade() < a.tamanho() + b.tamanho()) {
            throw new IllegalArgumentException("A capacidade de R e insuficiente.");
        }

        while (!a.vazia() && !b.vazia()) {
            if (a.primeiro() <= b.primeiro()) {
                r.insere(a.remove());
            } else {
                r.insere(b.remove());
            }
        }

        while (!a.vazia()) {
            r.insere(a.remove());
        }
        while (!b.vazia()) {
            r.insere(b.remove());
        }
    }

    public static void main(String[] args) {
        FilaCircular a = new FilaCircular(4);
        FilaCircular b = new FilaCircular(5);
        FilaCircular r = new FilaCircular(9);

        for (int x : new int[]{12, 35, 52, 64}) {
            a.insere(x);
        }
        for (int x : new int[]{5, 15, 23, 55, 75}) {
            b.insere(x);
        }

        merge(a, b, r);

        System.out.println("Primeiro de R: " + r.primeiro());
        System.out.println("Ultimo de R: " + r.ultimo());

        StringBuilder resultado = new StringBuilder("Fila R: ");
        while (!r.vazia()) {
            resultado.append(r.remove()).append(' ');
        }
        System.out.println(resultado.toString().trim());
    }
}

class FilaCircular {
    private final int[] elementos;
    private int inicio;
    private int fim;
    private int quantidade;

    public FilaCircular(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que zero.");
        }
        elementos = new int[capacidade];
        inicio = 0;
        fim = -1;
        quantidade = 0;
    }

    public boolean cheia() {
        return quantidade == elementos.length;
    }

    public boolean vazia() {
        return quantidade == 0;
    }

    public void insere(int elemento) {
        if (cheia()) {
            throw new IllegalStateException("Nao e possivel inserir: fila cheia.");
        }
        fim = (fim + 1) % elementos.length;
        elementos[fim] = elemento;
        quantidade++;
    }

    public int remove() {
        if (vazia()) {
            throw new IllegalStateException("Nao e possivel remover: fila vazia.");
        }
        int elemento = elementos[inicio];
        inicio = (inicio + 1) % elementos.length;
        quantidade--;
        return elemento;
    }

    public int primeiro() {
        if (vazia()) {
            throw new IllegalStateException("A fila esta vazia.");
        }
        return elementos[inicio];
    }

    public int ultimo() {
        if (vazia()) {
            throw new IllegalStateException("A fila esta vazia.");
        }
        return elementos[fim];
    }

    public int tamanho() {
        return quantidade;
    }

    public int capacidade() {
        return elementos.length;
    }
}
