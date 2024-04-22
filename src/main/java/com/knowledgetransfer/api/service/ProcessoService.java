package com.knowledgetransfer.api.service;

import com.knowledgetransfer.api.DTO.*;
import com.knowledgetransfer.api.model.CheckboxAlternativa;
import com.knowledgetransfer.api.model.Processo;
import com.knowledgetransfer.api.model.Recorrencia;
import com.knowledgetransfer.api.model.RecorrenciaProcesso;
import com.knowledgetransfer.api.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    public ProcessoCadastradoDTO cadastrar(ProcessoDTO dados){
        Processo processo = processoRepository.save(new Processo(dados));
        ProcessoCadastradoDTO processoCadastradoDTO = new ProcessoCadastradoDTO(processo.getId(),processo.getNome(), processo.getDescricao());
        return processoCadastradoDTO;
    }

    public void atualizar(AtualizacaoProcessoDTO dados) {
        Processo processo = processoRepository.getReferenceById(dados.id());
        if(dados.descricao()!=null) processo.setDescricao(dados.descricao());
        if(dados.nome()!=null) processo.setNome(dados.nome());
    }

    public Page<ListagemProcessoDTO> listar(Pageable paginacao){
        //retornar como list
        //return repository.findAll(paginacao).stream().map(ListagemFormularioDTO::new).toList();
        return processoRepository.findAll(paginacao).map(ListagemProcessoDTO::new);

    }

    public void deletar(Long id){
        processoRepository.deleteById(id);
    }

    public ListagemProcessoDTO encontrarProcesso(Long id){
        try{
            Processo processo = processoRepository.getReferenceById(id);
            return new ListagemProcessoDTO(processo);
        }
        catch(Exception e){
            return null;
        }
    }

    //public List<RecorrenciaProcessoDTO> encontrarIndicados(EncontrarIndicadosDTO indicados){
    public RecorrenciaDTO encontrarIndicados(EncontrarIndicadosDTO indicados){
//    public void encontrarIndicados(@RequestBody Object indicados){
        System.out.println(indicados);
        List<CheckboxAlternativa> respostas_alternativas=new ArrayList<CheckboxAlternativa>();
        for (int i = 0; i < indicados.respostas_alternativas().size(); i++) {
            if(indicados.respostas_alternativas().get(i).checked()){
                CheckboxAlternativa checkboxAlternativa = new CheckboxAlternativa(indicados.respostas_alternativas().get(i));
                respostas_alternativas.add(checkboxAlternativa);
            }

        }
        List<CheckboxAlternativa> temp = new ArrayList<CheckboxAlternativa>(respostas_alternativas.size());
        for (int i = 0; i < respostas_alternativas.size(); i++) {
            temp.add(new CheckboxAlternativa());
        }
        mergesort(respostas_alternativas,temp,0,respostas_alternativas.size()-1);
        String processo_atual="";
        List<RecorrenciaProcesso> recorrenciaProcessos = new ArrayList<RecorrenciaProcesso>();
        for (int i = 0; i < respostas_alternativas.size(); i++) {
            if(!processo_atual.equals(respostas_alternativas.get(i).getProcesso_nome())){
                //processos.add(processoRepository.getReferenceById((respostas_alternativas.get(i).getProcesso_id())));
                recorrenciaProcessos.add(new RecorrenciaProcesso(respostas_alternativas.get(i).getProcesso_id(), 1L));
                processo_atual=respostas_alternativas.get(i).getProcesso_nome();
            }
            else
                recorrenciaProcessos.get(recorrenciaProcessos.size()-1).setRecorrenciaPlus1();
        }
        List<RecorrenciaProcesso> temp2 = new ArrayList<>();
        for (int i = 0; i < recorrenciaProcessos.size(); i++) {
            temp2.add(new RecorrenciaProcesso());
        }
        mergersortRecorrencia(recorrenciaProcessos,temp2,0,recorrenciaProcessos.size()-1);

//        List<ProcessoDTO> processos=new ArrayList<ProcessoDTO>();
//        for (int i = 0; i < recorrenciaProcessos.size(); i++) {
//            //processos.add(new ProcessoDTO(processoRepository.getReferenceById(recorrenciaProcessos.get(i).getId())));
//        }
        List<RecorrenciaProcessoDTO> recorrenciaProcessosDTO=new ArrayList<RecorrenciaProcessoDTO>();
        Long total = 0l;
        for (int i = 0; i < recorrenciaProcessos.size(); i++) {
            recorrenciaProcessos.get(i).setDescricao(processoRepository.getReferenceById(recorrenciaProcessos.get(i).getId()).getDescricao());
            total+=recorrenciaProcessos.get(i).getRecorrencia();
            //recorrenciaProcessosDTO.add(recorrenciaProcessos.get(i));
            Processo processo = processoRepository.getReferenceById(recorrenciaProcessos.get(i).getId());
            recorrenciaProcessos.get(i).setProcesso_nome(processo.getNome());
            recorrenciaProcessos.get(i).setDescricao(processo.getDescricao());
            recorrenciaProcessosDTO.add(new RecorrenciaProcessoDTO(recorrenciaProcessos.get(i)));
            //recorrenciaProcessosDTO.add(new RecorrenciaProcesso(recorrenciaProcessos.get(i).getId(),recorrenciaProcessos.get(i).getRecorrencia(),processo.getNome(),processo.getDescricao()));
            //processos.add(new ProcessoDTO(processoRepository.getReferenceById(recorrenciaProcessos.get(i).getId())));
        }
        RecorrenciaDTO recorrenciaDTO = new RecorrenciaDTO(new Recorrencia(total,recorrenciaProcessosDTO));
        return recorrenciaDTO;
    }

    void mergersortRecorrencia(List<RecorrenciaProcesso> A, List<RecorrenciaProcesso> temp, int l, int r) {
        int mid = (l+r)/2; // Select midpoint
        if (l == r) return; // List has one element
        mergersortRecorrencia(A, temp, l, mid); // Mergesort first half
        mergersortRecorrencia(A, temp, mid+1, r); // Mergesort second half
        for (int i=l; i<=r; i++) // Copy subarray to temp
            temp.set(i,A.get(i));
        int i1 = l; int i2 = mid + 1;
        for (int curr=l; curr<=r; curr++) {
            if (i1 == mid+1) // Left sublist exhausted
                A.set(curr,temp.get(i2++));
                //A[curr] = temp[i2++];
            else if (i2 > r) // Right sublist exhausted
                A.set(curr,temp.get(i1++));
                //A[curr] = temp[i1++];
            else if (temp.get(i1).getRecorrencia()>temp.get(i2).getRecorrencia()) // Get bigger
                //else if (temp[i1]<temp[i2]) // Get smaller
                A.set(curr,temp.get(i1++));
                //A[curr] = temp[i1++];
            else A.set(curr,temp.get(i2++));
            //else A[curr] = temp[i2++];
        }
    }

    void mergesort(List<CheckboxAlternativa> A,List<CheckboxAlternativa> temp, int l, int r){
        int mid = (l+r)/2; // Select midpoint
        if (l == r) return; // List has one element
        mergesort(A, temp, l, mid); // Mergesort first half
        mergesort(A, temp, mid+1, r); // Mergesort second half
        for (int i=l; i<=r; i++) // Copy subarray to temp
            temp.set(i,A.get(i));
        int i1 = l; int i2 = mid + 1;
        for (int curr=l; curr<=r; curr++) {
            if (i1 == mid+1) // Left sublist exhausted
                A.set(curr,temp.get(i2++));
                //A[curr] = temp[i2++];
            else if (i2 > r) // Right sublist exhausted
                A.set(curr,temp.get(i1++));
                //A[curr] = temp[i1++];
            else if (temp.get(i1).getProcesso_id()>temp.get(i2).getProcesso_id()) // Get bigger
            //else if (temp[i1]<temp[i2]) // Get smaller
                A.set(curr,temp.get(i1++));
                //A[curr] = temp[i1++];
            else A.set(curr,temp.get(i2++));
            //else A[curr] = temp[i2++];
        }
    }

}

//class RecorrenciaProcesso {
//    private Long id;
//    private Long recorrencia;
//
//    private String processo_nome;
//
//    public RecorrenciaProcesso(Long id, Long recorrencia, String processo_nome) {
//        this.id = id;
//        this.recorrencia = recorrencia;
//        this.processo_nome=processo_nome;
//    }
//    public RecorrenciaProcesso() {
//    }
//
//    public String getProcesso_nome() {
//        return processo_nome;
//    }
//
//    public void setProcesso_nome(String processo_nome) {
//        this.processo_nome = processo_nome;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getRecorrencia() {
//        return recorrencia;
//    }
//
//    public void setRecorrencia(Long recorrencia) {
//        this.recorrencia = recorrencia;
//    }
//    public void setRecorrenciaPlus1() {
//        this.recorrencia++;
//    }
//}
