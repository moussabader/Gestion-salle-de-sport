<?php

namespace App\Controller;

use App\Entity\Client;
use App\Entity\Cours;
use App\Entity\Reservations;
use App\Form\ReservationsType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/reservations")
 */
class ReservationsController extends AbstractController
{
    /**
     * @Route("/", name="reservations_index", methods={"GET"})
     */
    public function index(): Response
    {
        $reservations = $this->getDoctrine()
            ->getRepository(Reservations::class)
            ->findAll();

        return $this->render('reservations/index.html.twig', [
            'reservations' => $reservations,
        ]);
    }

    /**
     * @Route("/new/{id}", name="reservations_new", methods={"GET","POST"})
     */
    public function new(Request $request,$id): Response
    {
            $reservation = new Reservations();
         $cours =$this->getDoctrine()
            ->getRepository(Cours::class)
            ->find($id);

        $user =$this->getDoctrine()
            ->getRepository(Client::class)
            ->find(1);
            $reservation->setIdCours($cours);
            $reservation->setIdUser($user);

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($reservation);
            $entityManager->flush();

            return $this->redirectToRoute('reservations_index');



    }

    /**
     * @Route("/{id}", name="reservations_show", methods={"GET"})
     */
    public function show(Reservations $reservation): Response
    {
        return $this->render('reservations/show.html.twig', [
            'reservation' => $reservation,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="reservations_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Reservations $reservation): Response
    {
        $form = $this->createForm(ReservationsType::class, $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reservations_index');
        }

        return $this->render('reservations/edit.html.twig', [
            'reservation' => $reservation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/delete/{id}", name="reservation_delete")
     */
    public function delete($id)
    {

        $res =$this->getDoctrine()
            ->getRepository(Reservations::class)
            ->find($id);
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($res);
        $entityManager->flush();
        return $this->redirectToRoute('reservations_index');

    }
}
