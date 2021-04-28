<?php

namespace App\Controller;

use App\Entity\Cours;
use App\Form\CoursType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/cours")
 */
class CoursController extends AbstractController
{


    public function count()
    {
        $count = 0;
        $em = $this->getDoctrine()->getManager();
        $commentaire = $em->getRepository(Cours::class)->findAll();
        foreach ($commentaire as $e){
            $count = $count + 1;
        }

        return $count;

    }
    /**
     * @Route("/", name="cours_index", methods={"GET"})
     */
    public function index(): Response
    {
        $count = $this->count();
        $cours = $this->getDoctrine()
            ->getRepository(Cours::class)
            ->findAll();

        return $this->render('cours/index.html.twig', [
            'cours' => $cours,
            'count' =>$count
        ]);
    }
    /**
     * @Route("/ss", name="ss", methods={"GET"})
     */
    public function indexs(): Response
    {
        $count = $this->count();
        $cours = $this->getDoctrine()
            ->getRepository(Cours::class)
            ->findAll();

        return $this->render('cours/stat.html.twig', [
            'cours' => $cours,
            'count' =>$count
        ]);
    }


    /**
     * @Route("/ClientIndex", name="Client_index", methods={"GET"})
     */
    public function ClientIndex(): Response
    {
        $cours = $this->getDoctrine()
            ->getRepository(Cours::class)
            ->findAll();

        return $this->render('cours/show.html.twig', [
            'cours' => $cours,
        ]);
    }

    /**
     * @Route("/cours/{id}", name="detailsCours", methods={"GET"})
     */
    public function detailsCours($id): Response
    {
        $cours = $this->getDoctrine()
            ->getRepository(Cours::class)
            ->find($id);

        return $this->render('cours/DetailsCours.html.twig', [
            'cours' => $cours,
        ]);
    }

    /**
     * @Route("/new", name="cours_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $cour = new Cours();
        $form = $this->createForm(CoursType::class, $cour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $cour->setImage("3.jpg");
            $cour->getUploadFile();
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($cour);
            $entityManager->flush();

            return $this->redirectToRoute('cours_index');
        }

        return $this->render('cours/new.html.twig', [
            'cour' => $cour,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="cours_show", methods={"GET"})
     */
    public function show(Cours $cour): Response
    {
        return $this->render('cours/show.html.twig', [
            'cour' => $cour,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="cours_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Cours $cour): Response
    {
        $form = $this->createForm(CoursType::class, $cour);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('cours_index');
        }

        return $this->render('cours/edit.html.twig', [
            'cour' => $cour,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/delete/{id}", name="cours_delete")
     */
    public function delete($id)
    {

        $cour =$this->getDoctrine()
            ->getRepository(Cours::class)
            ->find($id);
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($cour);
        $entityManager->flush();
        return $this->redirectToRoute('cours_index');

    }

    /**
     * @Route("/rate", name="rate_", methods={"POST"})
     */
    public function rateAction(\Symfony\Component\HttpFoundation\Request $request){
        $data = $request->getContent();
        $obj = json_decode($data,true);

        $em = $this->getDoctrine()->getManager();
        $rate =$obj['rate'];
        $idc = $obj['cours'];
        $cours = $em->getRepository(Cours::class)->find($idc);
        $note = ($cours->getRate()*$cours->getVote() + $rate)/($cours->getVote()+1);
        $cours->setVote($cours->getVote()+1);
        $cours->setRate($note);
        $em->persist($cours);
        $em->flush();
        return new Response($cours->getRate());
    }


}
