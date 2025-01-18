package br.com.alura.literalura_challenge.dto;

import java.util.List;

public class RespostaLivros {
    private int count;
    private String next;
    private String previous;
    private List<DadosBook> results;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<DadosBook> getResults() {
        return results;
    }

    public void setResults(List<DadosBook> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "------Resposta Livros-------" +
                "\ncount=" + count +
                "\nnext=" + next +
                "\nprevious=" + previous +
                "\nresults=" + results;
    }
}
